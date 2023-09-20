window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki
//main.html 프로젝트 테이블 설정
    const datatablesProject = document.getElementById('datatablesProject');
    if (datatablesProject) {
        new simpleDatatables.DataTable(datatablesProject, {
            autoWidth: false,
            columns: [
                { width: 100, targets: 0 }
            ],
        });
    }
//main.html 멤버 테이블 설정
    const datatablesMember = document.getElementById('datatablesMember');
    if (datatablesMember) {
        new simpleDatatables.DataTable(datatablesMember,{
            autoWidth: true,
            language: {
                style: {
                    fontSize: '20px' // 원하는 폰트 크기로 변경
                }
            }
        });
    }
//member.html 테이블 설정
    const memberList = document.getElementById('memberList');
    if (memberList) {
        new simpleDatatables.DataTable(memberList,{
            autoWidth: true,
            perPage: 25
        });
    }
//main.html design waiting list 테이블 설정
    const datatablesDWL = document.getElementById('designWaitingTable');
    if (datatablesDWL) {
        new simpleDatatables.DataTable(datatablesDWL,{
            autoWidth: true
        });
    }

    // const projectTable = document.getElementById('projectTable');
    // if (projectTable) {
    //     new simpleDatatables.DataTable(projectTable,{
    //         perPage: 5,
    //         search: false
    //     });
    // }

});
