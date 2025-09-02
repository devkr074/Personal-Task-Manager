INSERT INTO categories (name, description, color, created_at)
VALUES ('Work', 'Work-related tasks and projects', '#007bff', CURRENT_TIMESTAMP),
('Personal', 'Personal activities and errands', '#28a745', CURRENT_TIMESTAMP),
('Health', 'Health and fitness related tasks', '#dc3545', CURRENT_TIMESTAMP),
('Learning', 'Educational and skill development', '#ffc107', CURRENT_TIMESTAMP),
('Shopping', 'Shopping lists and purchases', '#17a2b8', CURRENT_TIMESTAMP);
INSERT INTO tasks (title, description, priority, status, due_date, category_id, created_at, updated_at)
VALUES ('Complete project documentation', 'Write comprehensive documentation for the Spring Boot project', 'HIGH', 'PENDING', '2024-12-15 17:00:00', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Buy groceries', 'Weekly grocery shopping - milk, bread, vegetables', 'MEDIUM', 'PENDING', '2024-12-10 18:00:00', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Morning workout', 'Cardio session at the gym', 'LOW', 'COMPLETED', '2024-12-08 07:00:00', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Learn Spring Security', 'Study Spring Security documentation and tutorials', 'MEDIUM', 'IN_PROGRESS', '2024-12-20 23:59:00', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Team meeting preparation', 'Prepare slides and agenda for weekly team meeting', 'HIGH', 'PENDING', '2024-12-12 09:00:00', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);