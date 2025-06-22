CREATE TABLE IF NOT EXISTS tasks (
    -- PRIMARY FIELDS
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(255) NOT NULL DEFAULT 'PENDING',
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE RESTRICT ON UPDATE CASCADE,

    -- AUDIT FIELDS
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- INDEXES
CREATE INDEX idx_tasks_active ON tasks(id) WHERE deleted = FALSE;
CREATE INDEX idx_tasks_user_id ON tasks(user_id);
CREATE INDEX idx_tasks_user_status ON tasks(user_id, status);