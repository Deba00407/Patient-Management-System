-- Enable UUID generation
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Drop table if exists (for clean dev runs)
DROP TABLE IF EXISTS patients;

-- Create table
CREATE TABLE patients (
      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
      full_name VARCHAR(255) NOT NULL,
      email_address VARCHAR(255) NOT NULL UNIQUE,
      phone_number VARCHAR(20) NOT NULL UNIQUE,
      address TEXT NOT NULL,
      date_of_birth DATE NOT NULL,
      gender VARCHAR(10) NOT NULL,
      registration_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Optional index (already created via UNIQUE, but keeping explicit index for registration_date)
CREATE INDEX idx_reg_date ON patients(registration_date);

-- Insert sample data
INSERT INTO patients (
    full_name,
    email_address,
    phone_number,
    address,
    date_of_birth,
    gender
) VALUES
      (
          'Rahul Sharma',
          'rahul.sharma@example.com',
          '9876543210',
          '12 MG Road, Kolkata',
          '1992-05-14',
          'MALE'
      ),
      (
          'Ananya Sen',
          'ananya.sen@example.com',
          '9123456780',
          '45 Park Street, Kolkata',
          '1988-11-22',
          'FEMALE'
      ),
      (
          'Aman Verma',
          'aman.verma@example.com',
          '9988776655',
          '221 Salt Lake Sector V, Kolkata',
          '2000-02-01',
          'OTHER'
      ),
      (
          'Priya Das',
          'priya.das@example.com',
          '9012345678',
          '78 Ballygunge Circular Road, Kolkata',
          '1995-09-10',
          'FEMALE'
      ),
      (
          'Sourav Mitra',
          'sourav.mitra@example.com',
          '9090909090',
          '10 Hill Cart Road, Siliguri',
          '1993-03-18',
          'MALE'
      ),
      (
          'Neha Kapoor',
          'neha.kapoor@example.com',
          '8800112233',
          'Sector 18, Noida',
          '1997-07-07',
          'FEMALE'
      ),
      (
          'Rohan Gupta',
          'rohan.gupta@example.com',
          '7700223344',
          'Andheri East, Mumbai',
          '1991-12-03',
          'MALE'
      ),
      (
          'Meera Iyer',
          'meera.iyer@example.com',
          '6600334455',
          'T Nagar, Chennai',
          '1985-04-25',
          'FEMALE'
      ),
      (
          'Karan Singh',
          'karan.singh@example.com',
          '5500445566',
          'Rajouri Garden, Delhi',
          '1999-08-19',
          'MALE'
      ),
      (
          'Aisha Khan',
          'aisha.khan@example.com',
          '4400556677',
          'Banjara Hills, Hyderabad',
          '1994-06-30',
          'OTHER'
      );