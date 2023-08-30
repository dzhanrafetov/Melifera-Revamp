# Melifera-Revamp
Rewriting the backend and frontend of the Melifera project:

- Dockerized the project
- Resolving the hardcoded email sending link
- Implementing data fetching from an external API to retrieve the cities of Bulgaria(Open Feign)
- Improved Image Uploading: Now you can upload up to 5 photos to advertisements, and it prevents uploading images that are already uploaded for the same advertisement.
- Added a scheduling feature to automatically remove confirmation tokens after they have been sent to users via email, with a 3-minute expiration period.
- Implemented Password Update Features:
   - Sending an email with a password reset link: Users who forgot their password can enter their email address to receive a password reset link.
   - Verifying the old password before updating: Users who are logged into their accounts and wish to change their password are required to confirm their old password before setting a new one.
- Implemented Email Confirmation Resend Feature.
- Replaced Basic HTTP Authentication with JWT Authentication. Updated SecurityConfig to use JWT-based authorization.



 



  
