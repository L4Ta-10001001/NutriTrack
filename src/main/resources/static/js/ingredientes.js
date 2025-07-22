document.addEventListener('DOMContentLoaded', async function () {
  const tbody = document.getElementById('body-ingredientes');
  tbody.innerHTML = '<tr><td colspan="7">Cargando ingredientes...</td></tr>';

  try {
    const res = await fetch('http://localhost:8080/api/ingredientes');
    if (!res.ok) throw new Error('Error al obtener los ingredientes');
    const ingredientes = await res.json();

    if (ingredientes.length === 0) {
      tbody.innerHTML = '<tr><td colspan="7">No se encontraron ingredientes.</td></tr>';
      return;
    }

    tbody.innerHTML = ''; // limpiar tabla

    ingredientes.forEach(({ id, nombre, calorias, proteina, azucar, grasa, sodio }) => {
      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${id}</td>
        <td>${nombre}</td>
        <td>${calorias}</td>
        <td>${proteina}</td>
        <td>${azucar}</td>
        <td>${grasa}</td>
        <td>${sodio}</td>
      `;
      tbody.appendChild(row);
    });
  } catch (error) {
    console.error('Error cargando ingredientes:', error);
    tbody.innerHTML = '<tr><td colspan="7" class="text-danger">Error al cargar ingredientes.</td></tr>';
  }
});
