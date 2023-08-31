window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    const datatablesProject = document.getElementById('datatablesProject');
    if (datatablesProject) {
        new simpleDatatables.DataTable(datatablesProject,{
            autoWidth: false,
            columnDefs: [
                { width: '10%', targets : [0] },
                { width: '30%', targets : [1] },
                { width: '10%', targets : [2] },
                { width: '50%', targets : [3] }
            ]
        });
    }

    const datatablesMember = document.getElementById('datatablesMember');
    if (datatablesMember) {
        new simpleDatatables.DataTable(datatablesMember,{
            autoWidth: true
        });
    }

});
