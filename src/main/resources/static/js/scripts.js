document.addEventListener("DOMContentLoaded", () => {
    const API_URL = "/api/propiety";

    const form = document.getElementById("add-form");
    const propiedadList = document.getElementById("propiedad-list");
    const submitBtn = document.getElementById("submit-btn");
    const cancelBtn = document.getElementById("cancel-btn");
    const formTitle = document.getElementById("form-title");

    let editMode = false;
    let currentId = null;

    // Función para cargar propiedades
    const loadPropiedades = () => {
        fetch(API_URL)
            .then(response => response.json())
            .then(data => {
                propiedadList.innerHTML = data.map(prop => `
                    <tr>
                        <td>${prop.idPropiety}</td>
                        <td>${prop.direccion}</td>
                        <td>${prop.precio}</td>
                        <td>${prop.tamano}</td>
                        <td>${prop.descripcion}</td>
                        <td>
                            <button class="edit-btn" data-id="${prop.idPropiety}"
                                    data-direccion="${prop.direccion}"
                                    data-precio="${prop.precio}"
                                    data-tamano="${prop.tamano}"
                                    data-descripcion="${prop.descripcion}">Editar</button>
                            <button class="delete-btn" data-id="${prop.idPropiety}">Eliminar</button>
                        </td>
                    </tr>
                `).join("");
            })
            .catch(error => console.error("Error al cargar propiedades:", error));
    };

    // Función para resetear el formulario al estado inicial
    const resetForm = () => {
        form.reset();
        submitBtn.textContent = "Agregar";
        cancelBtn.style.display = "none";
        formTitle.textContent = "Agregar Propiedad";
        editMode = false;
        currentId = null;
    };

    // Cargar propiedades al inicio
    loadPropiedades();

    // Agregar/Actualizar propiedad
    form.addEventListener("submit", (e) => {
        e.preventDefault();

        const propiedad = {
            direccion: document.getElementById("direccion").value,
            precio: parseInt(document.getElementById("precio").value),
            tamano: document.getElementById("tamano").value,
            descripcion: document.getElementById("descripcion").value
        };

        let url = API_URL;
        let method = "POST";

        // Si estamos en modo edición, usamos PUT
        if (editMode && currentId) {
            url = `${API_URL}/${currentId}`;
            method = "PUT";
        }

        fetch(url, {
            method: method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(propiedad)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Error en la respuesta del servidor");
            }
            return response.json();
        })
        .then(() => {
            // Resetear el formulario
            resetForm();

            // Recargar la lista de propiedades
            loadPropiedades();
        })
        .catch(error => console.error(`Error al ${editMode ? 'actualizar' : 'guardar'} propiedad:`, error));
    });

    // Botón de cancelar edición
    cancelBtn.addEventListener("click", resetForm);

    // Delegación de eventos para botones de editar y eliminar
    propiedadList.addEventListener("click", (e) => {
        // Eliminar propiedad
        if (e.target.classList.contains("delete-btn")) {
            const id = e.target.getAttribute("data-id");

            if (confirm("¿Estás seguro de eliminar esta propiedad?")) {
                fetch(`${API_URL}/${id}`, { method: "DELETE" })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error("Error al eliminar");
                        }
                        loadPropiedades();
                    })
                    .catch(error => console.error("Error al eliminar propiedad:", error));
            }
        }

        // Editar propiedad
        if (e.target.classList.contains("edit-btn")) {
            const btn = e.target;
            currentId = btn.getAttribute("data-id");

            // Llenar el formulario con los datos actuales
            document.getElementById("direccion").value = btn.getAttribute("data-direccion");
            document.getElementById("precio").value = btn.getAttribute("data-precio");
            document.getElementById("tamano").value = btn.getAttribute("data-tamano");
            document.getElementById("descripcion").value = btn.getAttribute("data-descripcion");

            // Cambiar la interfaz para modo edición
            submitBtn.textContent = "Actualizar";
            cancelBtn.style.display = "inline-block";
            formTitle.textContent = "Editar Propiedad";
            editMode = true;

            // Scroll hasta el formulario
            form.scrollIntoView({ behavior: 'smooth' });
        }
    });
});