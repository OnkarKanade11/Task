// script.js
function addR1Question() {
    const form = document.querySelector('form');
    const questionContainer = document.createElement('div');

    const questionLabel = document.createElement('label');
    questionLabel.textContent = 'Question:';
    const questionInput = document.createElement('input');
    questionInput.type = 'text';
    questionInput.required = true;
    questionInput.name = `r1CheckQuestions[${form.querySelectorAll('div').length}].question`;

    const answerLabel = document.createElement('label');
    answerLabel.textContent = 'Correct Answer:';

    const yesRadio = document.createElement('input');
    yesRadio.type = 'radio';
    yesRadio.name = `r1CheckQuestions[${form.querySelectorAll('div').length}].correctAnswer`;
    yesRadio.value = 'true';

    const yesLabel = document.createElement('label');
    yesLabel.textContent = 'Yes';

    const noRadio = document.createElement('input');
    noRadio.type = 'radio';
    noRadio.name = `r1CheckQuestions[${form.querySelectorAll('div').length}].correctAnswer`;
    noRadio.value = 'false';

    const noLabel = document.createElement('label');
    noLabel.textContent = 'No';

    questionContainer.appendChild(questionLabel);
    questionContainer.appendChild(questionInput);
    questionContainer.appendChild(answerLabel);
    questionContainer.appendChild(yesRadio);
    questionContainer.appendChild(yesLabel);
    questionContainer.appendChild(noRadio);
    questionContainer.appendChild(noLabel);

    form.insertBefore(questionContainer, form.lastElementChild);
}

function addR2Question() {
    const form = document.querySelector('form');
    const questionContainer = document.createElement('div');

    const questionLabel = document.createElement('label');
    questionLabel.textContent = 'Question:';
    const questionInput = document.createElement('input');
    questionInput.type = 'text';
    questionInput.required = true;
    questionInput.name = `r2CheckQuestions[${form.querySelectorAll('div').length}].question`;

    questionContainer.appendChild(questionLabel);
    questionContainer.appendChild(questionInput);

    form.insertBefore(questionContainer, form.lastElementChild);
}