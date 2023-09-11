window.addEventListener('DOMContentLoaded', event => {

    /* DWL add 유효성 검사 통과유무 변수 */
    var projNameCheck = false; // 프로젝트 이름 확인
    var startDateCheck = false; // 시작 날짜 확인

    $(".dwl_add_button")
        .click(
            function () {


                /* 입력값 변수 */
                var proj_name = document.getElementById("proj_name"); // id 입력란
                var start_date = document.getElementById("start_datepicker");


                /* 아이디 유효성검사 */
                if (proj_name.value === "") {
                    alert("프로젝트 명을 입력해 주세요");
                    proj_name.focus();
                    projNameCheck = false;
                } else {
                    projNameCheck = true;
                }

                /* 비밀번호 확인 유효성 검사 */
                if (start_date.value === '') {
                    alert('시작 일을 선택해주세요');
                    start_date.focus();
                    startDateCheck = false;

                } else {
                    startDateCheck = true;
                }


                /* 최종 유효성 검사 */
                if (projNameCheck && startDateCheck) {
                    $("#dwl_add_form").submit();
                }
                return false;
            });

    /* DWL edit 유효성 검사 통과유무 변수 */
    var dwlEditProjNameCheck = false; // 프로젝트 이름 확인
    var dwlEditStartDateCheck = false; // 시작 날짜 확인

    $(".dwl_edit_submit_button")
        .click(
            function () {
                var dwlEditMemberNameCheck = true; // 중복된 인원 확인
                //console.log("수정 버튼 클릭");

                /* 입력값 변수 */
                var dwlEditProjName = document.getElementById("proj_name"); // id 입력란
                var dwlEditStartDate = document.getElementById("start_datepicker");


                /* 아이디 유효성검사 */
                if (dwlEditProjName.value === "") {
                    alert("프로젝트 명을 입력해 주세요");
                    dwlEditProjName.focus();
                    dwlEditProjNameCheck = false;
                } else {
                    dwlEditProjNameCheck = true;
                }

                /* 비밀번호 확인 유효성 검사 */
                if (dwlEditStartDate.value === '') {
                    alert('시작 일을 선택해주세요');
                    dwlEditStartDate.focus();
                    dwlEditStartDateCheck = false;

                } else {
                    dwlEditStartDateCheck = true;
                }

                var selects = $("select[name='m_num']");

                var selectedValues = [];

                selects.each(function () {
                    var selectedValue = $(this).val();

                    if (selectedValues.indexOf(selectedValue) !== -1) { // 중복된 선택된 값이 이미 존재하는 경우
                        alert("중복된 선택된 옵션 값이 있습니다.");
                        dwlEditMemberNameCheck = false; // 유효성 검사 실패로 처리하고 종료
                    }
                    selectedValues.push(selectedValue);
                });
                // console.log("dwlEditStartDateCheck"+dwlEditStartDateCheck);
                // console.log("dwlEditProjNameCheck"+dwlEditProjNameCheck);
                // console.log("dwlEditMemberNameCheck"+dwlEditMemberNameCheck);
                /* 최종 유효성 검사 */
                if (dwlEditStartDateCheck && dwlEditProjNameCheck && dwlEditMemberNameCheck) {
                    //    console.log("submit");
                    $("#dwl_edit_form").submit();
                }
                return false;
            });

//design waiting list progressbar setting
    $('#progressbar').on('change', function () {
        var row = $(this).closest("tr");

        var percentage = $(this).val();

        row.find('.custom-bar').css('width', `${percentage}%`);

    });


// /admin/designwaitinglist 스크립트 설정
    $(".dwl_edit_button").click(function () {

        var row = $(this).closest("tr");

        var dwSeqValue = row.find('input[name="dw_seq"]').val();

        window.location.href = '/admin/editDw?dw_seq=' + dwSeqValue;
    });

    $(".dwl_delete_button").click(function () {
        // Find the closest parent row to the clicked button
        var row = $(this).closest("tr");

        // Retrieve the value from the hidden input field within that row
        var dwSeqValue = row.find('input[name="dw_seq"]').val();
        var proNameValue = row.find('input[name="proj_name"]').val();
        // Now, you can use the mNumValue as needed, such as including it in a confirmation message
        var confirmationMessage = '\'' + proNameValue + '\' 프로젝트를 제거하시겠습니까? \n삭제하면 참여했던 프로젝트 관련 자료들이 말소됩니다.';

        // Display a confirmation dialog
        if (confirm(confirmationMessage)) {

            window.location.href = "/admin/deleteDw?dw_seq=" + dwSeqValue;

        }
    });


//design waiting list progressbar setting
    var slider = document.getElementById("progress");
    var output = document.getElementById("progressval");
    output.innerHTML = slider.value;

    slider.oninput = function () {
        output.innerHTML = this.value;
    }


//var counter = document.getElementById("memberCount").val(); // 새로운 input 요소의 고유한 이름을 위한 카운터 변수
    var counter = parseInt($("#memberCount").val());


    $("#memberAdd").click(function () {
        var originalSelect = $("select[id='selectMem0']"); //수정에서의 select
        //var originalSelect2 = $("select[id='member" + (counter - 1) + "']");  //추가 페이지에서의 select
        counter++;

        var newRemoveButton = $("<a>", {
            id: "newMemberRemove" + counter,
            class: "btn btn-light btn-outline-dark mb-md-3",
            text: "-"
        });
        var newSelect = $("<select>", {
            id: "member" + counter,
            name: "m_num",
            class: "form-select mb-md-3",
            style: "width: 35%; margin-right:1rem;"
        });
        var newSelectContainer = $("<div>", {
            id: "newSelect" + counter,
            style: "display: flex; align-items: center;",
            class: "mb-md-3"
        });
        // 기존 select 요소의 option들을 순회하면서 선택된 옵션을 제외하고 새로운 select에 추가
        originalSelect.find("option").each(function () {
            newSelect.append($(this).clone());
        });
        newSelectContainer.append(newSelect, newRemoveButton);

        $("#selectContainer").append(newSelectContainer);

        newRemoveButton.click(function () {
            $(this).prev("select").remove();
            $(this).parent().remove();
            $(this).remove();
            counter--;
        });
        $("#memberCount").val(counter);
    });

    $('.memberRemove').click(function () {
        $(this).prev("select").remove();
        $(this).parent().remove();
        $(this).remove();
        counter--;
    });

});