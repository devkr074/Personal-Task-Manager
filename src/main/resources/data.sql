INSERT INTO categories (id, name, description, color, created_at, updated_at)
VALUES (1, 'Work', 'Work-related tasks and projects', '#007bff', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Personal', 'Personal activities and errands', '#28a745', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Health', 'Health and fitness related tasks', '#dc3545', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Learning', 'Educational and skill development', '#ffc107', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Shopping', 'Shopping lists and purchases', '#17a2b8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Home', 'Household chores and maintenance', '#6f42c1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO tasks (id, title, description, priority, status, due_date, category_id, created_at, updated_at)
VALUES (1, 'Complete project documentation', 'Write comprehensive documentation for the Spring Boot project including setup instructions and API documentation', 'HIGH', 'PENDING', '2024-12-15 17:00:00', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Buy groceries', 'Weekly grocery shopping - milk, bread, vegetables, fruits, and household items', 'MEDIUM', 'PENDING', '2024-12-10 18:00:00', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Morning workout', 'Cardio session at the gym - 30 minutes treadmill and 15 minutes cycling', 'LOW', 'COMPLETED', '2024-12-08 07:00:00', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Learn Spring Security', 'Study Spring Security documentation and complete online tutorials for authentication and authorization', 'MEDIUM', 'IN_PROGRESS', '2024-12-20 23:59:00', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Team meeting preparation', 'Prepare slides and agenda for weekly team meeting. Review project status and plan next sprint', 'HIGH', 'PENDING', '2024-12-12 09:00:00', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Clean garage', 'Organize and clean the garage, donate unused items', 'LOW', 'PENDING', '2024-12-14 16:00:00', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Doctor appointment', 'Annual health checkup with family physician', 'MEDIUM', 'PENDING', '2024-12-11 14:30:00', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Read Spring Boot book', 'Continue reading "Spring Boot in Action" - Chapter 5-7', 'LOW', 'IN_PROGRESS', '2024-12-18 22:00:00', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Fix kitchen faucet', 'Replace the dripping kitchen faucet washer', 'MEDIUM', 'PENDING', '2024-12-13 12:00:00', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Plan vacation', 'Research and book summer vacation destinations and accommodations', 'LOW', 'PENDING', '2024-12-25 23:59:00', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
ALTER SEQUENCE CATEGORIES_SEQ RESTART WITH 7;
ALTER SEQUENCE TASKS_SEQ RESTART WITH 11;