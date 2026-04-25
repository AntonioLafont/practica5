# practica5

Carrito
POST /api/carrito — Se ejecuta automáticamente al pulsar "Añadir al Carrito" en cualquier vinilo de la página de Productos, pero solo si no existe ya un carrito activo en la sesión. Si el carrito ya existe, simplemente se reutiliza.
GET /api/carrito/{id} — Se ejecuta automáticamente al cargar la página del Carrito, para verificar que el carrito activo sigue existiendo antes de recuperar sus líneas.
PUT /api/carrito/{id} — Se ejecuta al completar una compra. En la página del Carrito, pulsar el botón "Completar Compra", rellenar el nombre y el email en el modal que aparece, y pulsar "Siguiente". En ese momento se actualiza el correo del carrito con el email introducido por el usuario.
DELETE /api/carrito/{id} — Se ejecuta en el segundo paso del mismo modal de compra. Tras pulsar "Siguiente" en el paso anterior, se muestra un resumen del pedido. Al pulsar "Confirmar Pedido", se elimina el carrito de la base de datos y se limpia la sesión local.

Líneas de Carrito
POST /api/carrito/{id}/lineas — Se ejecuta al pulsar el botón "Añadir al Carrito" en cualquier vinilo de la página de Productos. Si el artículo ya existe en el carrito, se suma la cantidad en lugar de crear una línea duplicada.
GET /api/carrito/{id}/lineas — Se ejecuta automáticamente al cargar la página del Carrito, mostrando todos los artículos añadidos en una tabla con nombre, precio unitario, cantidad y subtotal.
DELETE /api/carrito/{id}/lineas/{idLinea} — Se ejecuta al pulsar el botón "Eliminar" en cualquier fila de la tabla del carrito. Aparece un diálogo de confirmación antes de realizar el borrado. La tabla se actualiza automáticamente tras eliminar el artículo.
