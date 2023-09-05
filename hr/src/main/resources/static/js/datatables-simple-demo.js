window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    const datatablesProject = document.getElementById('datatablesProject');
    if (datatablesProject) {
        new simpleDatatables.DataTable(datatablesProject, {
            autoWidth: false,
            columns: [
                { width: 100, targets: 0 }
            ]
        });
    }

    const datatablesMember = document.getElementById('datatablesMember');
    if (datatablesMember) {
        new simpleDatatables.DataTable(datatablesMember,{
            autoWidth: true
        });
    }

    const datatablesDWL = document.getElementById('designWaitingTable');
    if (datatablesDWL) {
        new simpleDatatables.DataTable(datatablesDWL,{
            autoWidth: true
        });
    }



});
