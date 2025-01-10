CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE courses (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         category VARCHAR(100) NOT NULL
);

CREATE TABLE topics (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        message TEXT NOT NULL,
                        creation_date TIMESTAMP NOT NULL,
                        status VARCHAR(100) NOT NULL,
                        user_id BIGINT NOT NULL,
                        course_id BIGINT NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(id),
                        FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE replies (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         message TEXT NOT NULL,
                         creation_date TIMESTAMP NOT NULL,
                         topic_id BIGINT NOT NULL,
                         user_id BIGINT NOT NULL,
                         FOREIGN KEY (topic_id) REFERENCES topics(id),
                         FOREIGN KEY (user_id) REFERENCES users(id)
);