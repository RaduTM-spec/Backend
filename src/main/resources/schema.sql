-- Preload data into the users table
INSERT IGNORE INTO users (id, name, role, picture_url)
VALUES
    (1, 'John Doe', 'TEAM_LEADER', 'https://example.com/picture1.jpg'),
    (2, 'Jane Smith', 'MEMBER', 'https://example.com/picture2.jpg'),
    (3, 'Bob Johnson', 'MEMBER', 'https://example.com/picture3.jpg'),
    (4, 'Alice Brown', 'MENTOR', 'https://example.com/picture4.jpg');

-- Insert sample data for the 'teams' table
INSERT INTO teams (id, name, team_leader_id)
VALUES
    (1, 'Team A', 1),
    (2, 'Team B', 2);

-- Insert sample data for the 'team_memberships' table
INSERT INTO team_memberships (id, team_id, user_id)
VALUES
    (1, 1, 2),
    (2, 1, 3),
    (3, 2, 3);

-- Insert sample data for the 'activities' table
INSERT INTO activities (id, name, creator_id, deadline)
VALUES
    (1, 'Activity 1', 1, '2023-08-15'),
    (2, 'Activity 2', 2, '2023-09-30');

-- Insert sample data for the 'teams_activities' table
INSERT INTO teams_activities (id, team_id, activity_id)
VALUES
    (1, 1, 1),
    (2, 2, 2);

-- Insert sample data for the 'mentors_activities' table
INSERT INTO mentors_activities (id, mentor_id, activity_id)
VALUES
    (1, 4, 1);

-- Insert sample data for the 'assessment' table
INSERT INTO assessment (id, title, activity_id, mentor_id, user_id, grade, attended, comment)
VALUES
    (1, 'Assessment 1', 1, 4, 2, 9.5, true, 'Great work!'),
    (2, 'Assessment 2', 2, 4, 3, 8.5, true, 'Good job!'),
    (3, 'Assessment 3', 1, 4, 3, 7.5, true, 'Needs improvement.');

-- Additional foreign key constraint (assuming 'assessment' is self-referencing)
ALTER TABLE assessment
    ADD CONSTRAINT fk_assessment_user_id
        FOREIGN KEY (user_id)
            REFERENCES assessment(id);
'https://robohash.com/wilson.jpg');