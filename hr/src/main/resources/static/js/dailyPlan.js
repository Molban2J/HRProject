$(document).ready(
    function(){
            $(".plan_add_button").click(function() {
                // Clone the existing form and its contents
                const form = $("#addForm");
                var clonedForm = form.clone();

                // Clear the input values in the cloned form
                clonedForm.find("input[type='text']").val("");
                const selectOption = clonedForm.find("option").val();
                const pTag = clonedForm.find("p");
                const selectMem = clonedForm.find("select[id='member1']");
                clonedForm.find("select").remove();
                pTag.after(selectMem);
                // Append the cloned form to the DOM
                form.after(clonedForm);
            });

        var counter = parseInt($("#memberCount").val());

        $("#memberAdd").click(function () {
            counter++;

            const newRemoveButton = $("<a>", {
                id: "newMemberRemove" + counter,
                class: "btn btn-light btn-outline-dark mb-md-3",
                text: "-"
            });
            const newSelect = $("<select>", {
                id: "member" + counter,
                name: "m_num",
                class: "form-select mb-md-3",
                style: "width: 35%; margin-right:1rem;"
            });
            const newSelectContainer = $("<div>", {
                id: "newSelect" + counter,
                style: "display: flex; align-items: center;"
            });

            // Clone the options from the original select element and add them to the new select element
            $("select[id='member1'] option").each(function () {
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

    }
);