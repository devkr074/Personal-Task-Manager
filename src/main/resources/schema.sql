DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    color VARCHAR(7) DEFAULT '#6c757d',
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE INDEX idx_category_name ON categories(name);
CREATE INDEX idx_category_active ON categories(active);
CREATE TABLE tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    priority VARCHAR(10) NOT NULL DEFAULT 'MEDIUM',
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    due_date DATE,
    completed_at TIMESTAMP NULL,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    category_id BIGINT,
    CONSTRAINT fk_task_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL,
    CONSTRAINT chk_task_priority CHECK (priority IN ('LOW', 'MEDIUM', 'HIGH', 'URGENT')),
    CONSTRAINT chk_task_status CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED')),
    CONSTRAINT chk_task_title_length CHECK (CHAR_LENGTH(title) >= 3)
);
CREATE INDEX idx_task_status ON tasks(status);
CREATE INDEX idx_task_priority ON tasks(priority);
CREATE INDEX idx_task_due_date ON tasks(due_date);
CREATE INDEX idx_task_category ON tasks(category_id);
CREATE INDEX idx_task_created_at ON tasks(created_at);
CREATE INDEX idx_task_active ON tasks(active);
CREATE INDEX idx_task_status_priority ON tasks(status, priority);
CREATE INDEX idx_task_category_status ON tasks(category_id, status);
CREATE INDEX idx_task_due_date_status ON tasks(due_date, status);
INSERT INTO categories (name, description, color) VALUES
('Work', 'Professional and work-related tasks', '#007bff'),
('Personal', 'Personal tasks and activities', '#28a745'),
('Health', 'Health and fitness related tasks', '#dc3545'),
('Learning', 'Educational and skill development tasks', '#ffc107'),
('Finance', 'Financial and money-related tasks', '#6f42c1'),
('Home', 'Household chores and maintenance', '#fd7e14'),
('Social', 'Social activities and relationships', '#20c997'),
('Shopping', 'Shopping lists and purchases', '#e83e8c');