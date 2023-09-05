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


/* 유효성 검사 통과유무 변수 */
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


        //design waiting list progressbar setting
        $('#progressbar').on('change', function() {
            var row = $(this).closest("tr");

            // Calculate the percentage based on the slider value
            var percentage = $(this).val();

            // Update the progress bar's width with the calculated percentage
            row.find('.custom-bar').css('width', `${percentage}%`);

        });

    });


