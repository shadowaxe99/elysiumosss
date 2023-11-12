-- ElysiumOS Database Schema
-- This schema defines the structure for the ElysiumOS database.

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    session_token VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- AI Agents Table
CREATE TABLE IF NOT EXISTS ai_agents (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    ai_agent_state JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Worlds Table
CREATE TABLE IF NOT EXISTS worlds (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    world_customization_settings JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Transactions Table
CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    blockchain_transaction_data JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Events Table
CREATE TABLE IF NOT EXISTS events (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_time TIMESTAMP WITH TIME ZONE,
    end_time TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tasks Table
CREATE TABLE IF NOT EXISTS tasks (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    task_queue JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Matches Table
CREATE TABLE IF NOT EXISTS matches (
    id SERIAL PRIMARY KEY,
    arena_match_data JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Items Table
CREATE TABLE IF NOT EXISTS items (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    in_game_currency_balance INT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- NFTs Table
CREATE TABLE IF NOT EXISTS nfts (
    id SERIAL PRIMARY KEY,
    owner_id INT REFERENCES users(id),
    nft_ownership_record JSONB,
    smart_contract_address VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Devices Table
CREATE TABLE IF NOT EXISTS devices (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    device_connection_status JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Plugins Table
CREATE TABLE IF NOT EXISTS plugins (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    plugin_configurations JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Backups Table
CREATE TABLE IF NOT EXISTS backups (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    backup_data BYTEA,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Cloud Services Table
CREATE TABLE IF NOT EXISTS cloud_services (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    cloud_sync_status JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for faster query performance
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_ai_agents_user_id ON ai_agents(user_id);
CREATE INDEX idx_worlds_user_id ON worlds(user_id);
CREATE INDEX idx_transactions_user_id ON transactions(user_id);
CREATE INDEX idx_tasks_user_id ON tasks(user_id);
CREATE INDEX idx_items_name ON items(name);
CREATE INDEX idx_nfts_owner_id ON nfts(owner_id);
CREATE INDEX idx_devices_user_id ON devices(user_id);
CREATE INDEX idx_plugins_name ON plugins(name);
CREATE INDEX idx_backups_user_id ON backups(user_id);
CREATE INDEX idx_cloud_services_user_id ON cloud_services(user_id);

-- End of schema.sql