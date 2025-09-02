document.addEventListener('DOMContentLoaded', function () {
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(function (alert) {
        setTimeout(function () {
            const bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        }, 5000);
    });
    const deleteButtons = document.querySelectorAll('.btn-delete');
    deleteButtons.forEach(function (button) {
        button.addEventListener('click', function (e) {
            if (!confirm('Are you sure you want to delete this task?')) {
                e.preventDefault();
            }
        });
    });
    const forms = document.querySelectorAll('.needs-validation');
    forms.forEach(function (form) {
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        });
    });
    function updatePriorityColors() {
        const prioritySelects = document.querySelectorAll('select[name="priority"]');
        prioritySelects.forEach(function (select) {
            select.addEventListener('change', function () {
                const value = this.value;
                this.className = this.className.replace(/text-(danger|warning|success)/g, '');
                if (value === 'HIGH') {
                    this.classList.add('text-danger');
                } else if (value === 'MEDIUM') {
                    this.classList.add('text-warning');
                } else if (value === 'LOW') {
                    this.classList.add('text-success');
                }
            });
            select.dispatchEvent(new Event('change'));
        });
    }
    updatePriorityColors();
});
function formatDate(dateString) {
    const options = {
        year: 'numeric',
        month: 'short',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    };
    return new Date(dateString).toLocaleDateString('en-US', options);
}
function toggleTaskStatus(taskId) {
    console.log('Toggle task status for ID:', taskId);
}