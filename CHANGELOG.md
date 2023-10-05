# - CHANGELOG -


## Dockerization
- Dockerized the project for easier deployment and management.

## Email Sending
- Eliminated hardcoded email sending links, making the email system more flexible and maintainable.

## City Data Integration
- Implemented data fetching from an external API to retrieve the cities of Bulgaria using Open Feign, enhancing location-based features.

## Enhanced Image Uploading
- Improved image uploading capabilities:
    - Users can now upload up to 5 photos for each advertisement.
    - Prevented the upload of duplicate images for the same advertisement.

## Confirmation Token Management
- Added scheduling to automatically remove confirmation tokens after a 3-minute expiration period, improving security and efficiency.

## Password Update Features
- Introduced password update features:
    - Users can request a password reset link by providing their email address.
    - Users are required to verify their old password before setting a new one for improved account security.

## Email Confirmation Resend
- Implemented a feature allowing users to request the resending of email confirmation.

## JWT Authentication
- Replaced Basic HTTP Authentication with JWT Authentication, enhancing security.
- Updated SecurityConfig to use JWT-based authorization.

## Redis Integration
- Configured Redis for efficient token storage and session management.
- Utilized a Redis image from Docker for easy deployment.

## Elasticsearch Integration
- Integrated Elasticsearch for efficient search capabilities.
- Implemented fuzzy searching to provide more relevant search results to users.

## Logstash Data Migration
- Implemented Logstash to migrate data from MySQL to Elasticsearch.
- Configured Logstash pipelines to handle data migration for advertisements.

## Logout Feature
- Added a logout feature to clear user sessions and remove tokens from Redis, improving session handling.
