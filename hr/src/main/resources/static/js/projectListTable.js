$(document).ready(
    function() {
        //멤버 수정 버튼 스크립트 설정
        $(".pl_edit_button").click(function() {

            const row = $(this).closest("tr");

            const projIdValue = row.find('input[name="proj_id"]').val();

            window.location.href = '/admin/editProject?proj_id=' + projIdValue;
        });

        $(".pl_delete_button").click(function() {
            // Find the closest parent row to the clicked button
            const row = $(this).closest("tr");

            const projNameValue = row.find('input[name="proj_name"]').val();
            const projIdValue = row.find('input[name="proj_id"]').val();
            // Now, you can use the mNumValue as needed, such as including it in a confirmation message
            const confirmationMessage ='\''+ projNameValue+ '\' 프로젝트를 삭제하시겠습니까? \n삭제하면 해당 프로젝트 관련 자료들이 모두 말소됩니다.';

            // Display a confirmation dialog
            if (confirm(confirmationMessage)) {

                window.location.href = "/admin/deleteProject?proj_id="+projIdValue;

            }
        });


        var counter = parseInt($("#memberCount").val());


        $("#memberAdd").click(function() {
            var originalSelect = $("select[id='member1']"); //수정에서의 select
            var posSelect = $("select[id='importance']");
            counter++;

            var newRemoveButton = $("<a>", {
                id: "newMemberRemove" + counter,
                class:"btn btn-light btn-outline-dark",
                text: "-"
            });
            var newSelect = $("<select>", {
                id: "member"+counter,
                name: "m_num",
                class:"form-select",
                style:"width: 70%; margin-right:1rem;" });
            var newSelectMemberContainer = $("<div>", {
                id: "newMemSelect" + counter,
                class: "col-xl-6"
            });

            var newSelectPositionContainer = $("<div>", {
                id: "newPosSelect" + counter,
                class: "col-xl-6",
                style: "display: flex; align-items: center;"
            });
            var newSelectPosition = $("<select>", {
                name: "importance",
                class:"form-select",
                style:"width: 70%; margin-right:1rem;" });

            var newContainer = $("<div>", {
                class: "row mb-md-3"
            });
            // 기존 select 요소의 option들을 순회하면서 선택된 옵션을 제외하고 새로운 select에 추가
            originalSelect.find("option").each(function() {
                newSelect.append($(this).clone());
            });
            posSelect.find("option").each(function (){
                newSelectPosition.append($(this).clone());
            })
            newSelectMemberContainer.append(newSelect);
            newSelectPositionContainer.append(newSelectPosition, newRemoveButton);
            newContainer.append(newSelectMemberContainer, newSelectPositionContainer);
            $("#selectContainer").append(newContainer);

            newRemoveButton.click(function() {
                //$(this).prev("select").remove();
                $(this).parent().parent().remove();
                $(this).remove();
                counter--;
            });
            $("#memberCount").val(counter);
        });


        $('.projMemberRemove').click(function () {
            $(this).parent().parent().parent().remove();
            $(this).remove();
            counter--;
        });

        $('.proj_edit_button').click(function(){
            var endDateInput = document.getElementById("end_date");
            if (endDateInput.value === "") {
                endDateInput.value = '9998-12-31';
            }
        })


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
