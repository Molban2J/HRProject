$(document).ready(
    function () {

        //멤버 수정 버튼 스크립트 설정
        $(".pl_edit_button").click(function () {

            const row = $(this).closest("tr");

            const projIdValue = row.find('input[name="proj_id"]').val();

            window.location.href = '/admin/editProject?proj_id=' + projIdValue;
        });

        $(".pl_delete_button").click(function () {
            // Find the closest parent row to the clicked button
            const row = $(this).closest("tr");

            const projNameValue = row.find('input[name="proj_name"]').val();
            const projIdValue = row.find('input[name="proj_id"]').val();
            // Now, you can use the mNumValue as needed, such as including it in a confirmation message
            const confirmationMessage = '\'' + projNameValue + '\' 프로젝트를 삭제하시겠습니까? \n삭제하면 해당 프로젝트 관련 자료들이 모두 말소됩니다.';

            // Display a confirmation dialog
            if (confirm(confirmationMessage)) {

                window.location.href = "/admin/deleteProject?proj_id=" + projIdValue;

            }
        });


        var counter = parseInt($("#memberCount").val());


        $("#memberAdd").click(function () {
            var originalSelect = $("select[id='member1']"); //수정에서의 select
            var posSelect = $("select[id='importance']");
            counter++;

            var newRemoveButton = $("<a>", {
                id: "newMemberRemove" + counter,
                class: "btn btn-light btn-outline-dark",
                text: "-"
            });
            var newSelect = $("<select>", {
                id: "member" + counter+1,
                name: "m_num",
                class: "form-select",
                style: "width: 70%; margin-right:1rem;"
            });
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
                class: "form-select",
                style: "width: 70%; margin-right:1rem;"
            });

            var newContainer = $("<div>", {
                class: "row mb-md-3"
            });
            // 기존 select 요소의 option들을 순회하면서 선택된 옵션을 제외하고 새로운 select에 추가
            originalSelect.find("option").each(function () {
                newSelect.append($(this).clone());
            });
            posSelect.find("option").each(function () {
                newSelectPosition.append($(this).clone());
            })
            newSelectMemberContainer.append(newSelect);
            newSelectPositionContainer.append(newSelectPosition, newRemoveButton);
            newContainer.append(newSelectMemberContainer, newSelectPositionContainer);
            $("#selectContainer").append(newContainer);

            newRemoveButton.click(function () {
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


        let projNamePass = false;
        $('#proj_name').on('input',function(){

            const projName = {projNameValue: $('#proj_name').val()};


            $.ajax({
                    url: "/admin/projNameCheck.do",
                    type: "post",
                    data: projName,
                    dataType: "json",
                    success: function(data) {
                        const message = $("#message");
                        if (data.pass === 1) {
                            message.removeClass("text-danger").addClass("text-success");
                            projNamePass = true;
                        } else {
                            message.removeClass("text-success").addClass("text-danger");
                            projNamePass = false;
                        }
                        if($('#proj_name').val().length === 0){
                            message.text("");
                        } else {
                            message.text(data.message);
                        }
                    }
                }
            );
        });


        let memberPass = false;
        let startDatePass = false;
        $('.proj_add_button').click(function () {
            memberPass = false;
            startDatePass = false;


            const startDateInput = $('#start_date').val();
            if(startDateInput.length>0){
                startDatePass = true;
            } else{
                alert("시작일을 선택해 주세요");
                startDatePass = false;
                return false;
            }

            const selectContainer = $('#selectContainer').find("select");
            selectContainer.each(function(){
                if($(this).val() === "멤버를 선택해주세요"){
                    alert("정확한 인원을 선택해주세요");
                    memberPass = false;
                    return false;
                } else {
                    memberPass = true;
                }
            });

            if(memberPass && startDatePass && projNamePass){
                var endDateInput = document.getElementById("end_date");
                if (endDateInput.value === "") {
                    endDateInput.value = '9998-12-31';
                }
                $('#proj_add_form').submit();
            }

            return false;
        });

        $('#edit_proj_name').on('input',function(){
            projNamePass = false;
            const projValue = {projNameValue: $('#edit_proj_name').val(),
            projSeqValue : $('#proj_id').val()};


            $.ajax({
                    url: "/admin/editProjNameCheck.do",
                    type: "post",
                    data: projValue,
                    dataType: "json",
                    success: function(data) {
                        const message = $("#edit_message");
                        if (data.pass === 1) {
                            message.removeClass("text-danger").addClass("text-success");
                            projNamePass = true;
                        } else {
                            message.removeClass("text-success").addClass("text-danger");
                            projNamePass = false;
                        }
                        if($('#edit_proj_name').val().length === 0){
                            message.removeClass("text-success").addClass("text-danger");
                            message.text("프로젝트 명을 입력해주세요");
                            projNamePass = false;
                        } else {
                            message.text(data.message);
                        }
                    }
                }
            );
        });

        $('.proj_edit_button').click(function () {

            const startDateInput = $('#start_date').val();
            if(startDateInput.length>0){
                startDatePass = true;
            } else{
                alert("시작일을 선택해 주세요");
                startDatePass = false;
                return false;
            }

            const selectContainer = $('#selectContainer').find("select");
            selectContainer.each(function(){
                if($(this).val() === "멤버를 선택해주세요"){
                    alert("정확한 인원을 선택해주세요");
                    memberPass = false;
                    return false;
                } else {
                    memberPass = true;
                }
            });

            if(memberPass && startDatePass && projNamePass){
                var endDateInput = document.getElementById("end_date");
                if (endDateInput.value === "") {
                    endDateInput.value = '9998-12-31';
                }
                $('#proj_edit_form').submit();
            }

            return false;
        })


    });
