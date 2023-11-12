// automation.js - Handles the Automation Station's UI interactions and API integrations

document.addEventListener('DOMContentLoaded', function() {
  const automationDashboard = document.getElementById('automationDashboard');
  const taskQueueDisplay = document.getElementById('taskQueue');

  // Initialize the task queue from the user's session
  let taskQueue = JSON.parse(sessionStorage.getItem('taskQueue')) || [];

  // Display the current task queue
  function renderTaskQueue() {
    taskQueueDisplay.innerHTML = '';
    taskQueue.forEach((task, index) => {
      const taskElement = document.createElement('div');
      taskElement.className = 'task';
      taskElement.innerHTML = `
        <span>${task.name}</span>
        <button onclick="removeTask(${index})">Remove</button>
      `;
      taskQueueDisplay.appendChild(taskElement);
    });
  }

  // Add a new task to the queue
  window.addTask = function(task) {
    taskQueue.push(task);
    sessionStorage.setItem('taskQueue', JSON.stringify(taskQueue));
    renderTaskQueue();
    // Send task to the backend for scheduling
    fetch('/api/automation/addTask', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${userSessionToken}`
      },
      body: JSON.stringify(task)
    })
    .then(response => response.json())
    .then(data => {
      if (data.success) {
        console.log('Task scheduled successfully');
      } else {
        console.error('Failed to schedule task');
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
  };

  // Remove a task from the queue
  window.removeTask = function(index) {
    taskQueue.splice(index, 1);
    sessionStorage.setItem('taskQueue', JSON.stringify(taskQueue));
    renderTaskQueue();
    // Update the backend about the task removal
    fetch('/api/automation/removeTask', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${userSessionToken}`
      },
      body: JSON.stringify({ index })
    })
    .then(response => response.json())
    .then(data => {
      if (data.success) {
        console.log('Task removed successfully');
      } else {
        console.error('Failed to remove task');
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
  };

  // Load the initial task queue
  renderTaskQueue();
});