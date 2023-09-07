/*!
    * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2023 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

// 모달 설정 -> 보류
// var myModal = document.getElementById('addMemberModal')
// var myInput = document.getElementById('inputFirstName')
//
// myModal.addEventListener('shown.bs.modal', function () {
//     myInput.focus()
// })


/* Member 유효성 검사 통과유무 변수 */
var nameCheck = false; // 아이디
var pos1Check = false; // 비번 확인
var pos2Check = false; // 비번 확인 일치 확인


// 사원 추가 유효성 검사
$(document).ready(
    function() {
        $(".join_button")
            .click(
                function() {


                    /* 입력값 변수 */
                    var name = document.getElementById("name"); // id 입력란
                    var position1 = document.getElementById("position1");
                    var position2 = document.getElementById("position2");


                    /* 아이디 유효성검사 */
                    if (name.value === "") {
                        alert("이름을 입력해 주세요");
                        name.focus();
                        nameCheck = false;
                    } else {
                        nameCheck = true;
                    }

                    /* 비밀번호 확인 유효성 검사 */
                    if (position1.value === '') {
                        alert('직책을 선택해주세요');
                        position1.focus();
                        pos1Check = false;

                    } else {
                        pos1Check = true;
                    }

                    if (position2.value === '') {
                        alert('직급을 선택해주세요')
                        position2.focus();
                        pos2Check = false;
                    } else {
                        pos2Check = true;
                    }



                    /* 최종 유효성 검사 */
                    if (nameCheck && pos1Check && pos2Check) {
                        $("#join_form").submit();
                    }
                    return false;
                });


        //멤버 수정 버튼 스크립트 설정
        $(".edit_button").click(function() {

            var row = $(this).closest("tr");

            var mNumValue = row.find('input[name="m_num"]').val();

            window.location.href = '/admin/editMember?m_num=' + mNumValue;
        });

        $(".delete_button").click(function() {
            // Find the closest parent row to the clicked button
            var row = $(this).closest("tr");

            // Retrieve the value from the hidden input field within that row
            var mNumValue = row.find('input[name="m_num"]').val();
            var nameValue = row.find('input[name="name"]').val();
            // Now, you can use the mNumValue as needed, such as including it in a confirmation message
            var confirmationMessage ='\''+ nameValue+ '\'님을 명단에서 삭제하시겠습니까? \n삭제하면 참여했던 프로젝트, 기타 자료들이 모두 말소됩니다.';

            // Display a confirmation dialog
            if (confirm(confirmationMessage)) {

                window.location.href = "/admin/deleteMember?m_num="+mNumValue;

            }
        });

        /* DWL 유효성 검사 통과유무 변수 */
        var projNameCheck = false; // 프로젝트 이름 확인
        var startDateCheck = false; // 시작 날짜 확인

        $(".dwl_add_button")
            .click(
                function() {


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

        var counter = document.getElementById("memberCount").val(); // 새로운 input 요소의 고유한 이름을 위한 카운터 변수


        $("#memberAdd").click(function() {
            var originalSelect = $("select[name='m_num']");
            counter++;
            var selectedOptionValue = originalSelect.val(); // 이전 select에서 선택된 옵션 값 가져오기
            var removeButton = $("a[id='memberRemove']");
            var newRemoveButton = $("<a>", {
                id: "newMemberRemove" + counter,
                class:"btn btn-light btn-outline-dark",
                text: "-"
                });
            var newSelect = $("<select>", {
                id: "member"+counter,
                name: "m_num",
                class:"form-select",
                style:"width: 35%; margin-right:1rem;" });
            var newSelectContainer = $("<div>", {
                id: "newSelect" + counter,
                style: "display: flex; align-items: center;",
                class: "mb-md-3"
            });
            // 기존 select 요소의 option들을 순회하면서 선택된 옵션을 제외하고 새로운 select에 추가
            originalSelect.find("option").each(function() {

                    newSelect.append($(this).clone());

            });
            newSelectContainer.append(newSelect,newRemoveButton);

            $("#selectContainer").append(newSelectContainer);

            newRemoveButton.click(function() {
                $(this).prev("select").remove();
                $(this).parent().remove();
                $(this).remove();
                counter--;
            });

            removeButton.click(function() {
                $(this).prev("select").remove();
                $(this).parent().remove();
                $(this).remove();
                counter--;
            });
            $("#memberCount").val(counter);
        });


        //design waiting list progressbar setting
        $('#progressbar').on('change', function() {
            var row = $(this).closest("tr");

            var percentage = $(this).val();

            row.find('.custom-bar').css('width', `${percentage}%`);

        });


        // /admin/designwaitinglist 스크립트 설정
        $(".dwl_edit_button").click(function() {

            var row = $(this).closest("tr");

            var dwSeqValue = row.find('input[name="dw_seq"]').val();

            window.location.href = '/admin/editDw?dw_seq=' + dwSeqValue;
        });

        $(".dwl_delete_button").click(function() {
            // Find the closest parent row to the clicked button
            var row = $(this).closest("tr");

            // Retrieve the value from the hidden input field within that row
            var dwSeqValue = row.find('input[name="dw_seq"]').val();
            var proNameValue = row.find('input[name="proj_name"]').val();
            // Now, you can use the mNumValue as needed, such as including it in a confirmation message
            var confirmationMessage ='\''+ proNameValue+ '\' 프로젝트를 제거하시겠습니까? \n삭제하면 참여했던 프로젝트 관련 자료들이 말소됩니다.';

            // Display a confirmation dialog
            if (confirm(confirmationMessage)) {

                window.location.href = "/admin/deleteDw?dw_seq="+dwSeqValue;

            }
        });


        //design waiting list progressbar setting
        var slider = document.getElementById("progress");
        var output = document.getElementById("progressval");
        output.innerHTML = slider.value;

        slider.oninput = function() {
            output.innerHTML = this.value;
        }




    });


