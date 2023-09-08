$(document).ready(
    function() {
        //멤버 수정 버튼 스크립트 설정
        $(".pl_edit_button").click(function() {

            const row = $(this).closest("tr");

            const projNameValue = row.find('input[name="proj_name"]').val();

            window.location.href = '/admin/editProject?m_num=' + projNameValue;
        });

        $(".pl_delete_button").click(function() {
            // Find the closest parent row to the clicked button
            const row = $(this).closest("tr");

            // Retrieve the value from the hidden input field within that row
            const projNameValue = row.find('input[name="proj_name"]').val();
            // Now, you can use the mNumValue as needed, such as including it in a confirmation message
            const confirmationMessage ='\''+ projNameValue+ '\' 프로젝트를 삭제하시겠습니까? \n삭제하면 해당 프로젝트 관련 자료들이 모두 말소됩니다.';

            // Display a confirmation dialog
            if (confirm(confirmationMessage)) {

                window.location.href = "/admin/deleteProject?proj_name="+projNameValue;

            }
        });

        // $(".proj_submit_button").click(function() {
        //     const selectContainer = $('#selectContainer');
        //
        //     const data = [];
        //     const proj_name = $('#proj_name').val();
        //     const start_date = $('#start_date').val();
        //     const end_date = $('#end_Date').val();
        //     const member = [];
        //     selectContainer.find("div").each(function() {
        //         selectContainer.find($('#m_num'))
        //     });
        //     $.ajax({
        //         url: '/admin/addProject.do',
        //         type: 'POST',
        //         contentType: 'application/json',
        //         data: JSON.stringify(data),
        //         success: function(response) {
        //             // 성공적으로 응답을 받았을 때 처리할 로직
        //             console.log(response);
        //         },
        //     });
        // });
    });
