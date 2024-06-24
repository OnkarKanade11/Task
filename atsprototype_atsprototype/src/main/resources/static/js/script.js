let r1QuestionCount = 0;

function addR1Question() {
    const container = document.getElementById('r1QuestionsContainer');
    const questionDiv = document.createElement('div');
    questionDiv.className = 'r1-question';
    
    questionDiv.innerHTML = `
        <label for="r1Question${r1QuestionCount}">Question:</label>
        <input type="text" id="r1Question${r1QuestionCount}" name="r1Questions" required>
        
        <label>Correct Answer:</label>
        <input type="radio" name="r1Answers[${r1QuestionCount}]" value="true" required> Yes
        <input type="radio" name="r1Answers[${r1QuestionCount}]" value="false" required> No
    `;
    
    container.appendChild(questionDiv);
    r1QuestionCount++;
}

// You can add more functions here if needed, for example:

function validateForm() {
    // Add any form validation logic here
    return true; // Return false if validation fails
}

// Add event listener when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');
    if (form) {
        form.addEventListener('submit', function(event) {
            if (!validateForm()) {
                event.preventDefault(); // Prevent form submission if validation fails
            }
        });
    }

    // Add initial R1 question if needed
    addR1Question();
});