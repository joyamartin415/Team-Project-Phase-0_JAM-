public class Employee {
    private int empID;
    private String fname;
    private String lname;
    private String email;
    private String dob;
    private String hireDate;
    private double salary;
    private String ssn;
    private int addressID;
    private int job_title_id;
    private int div_ID;
    private String mobilePhone;
    private String emergencyContactName;
    private String emergencyContactPhone;

    public Employee(int empID,String fname,String lname,
        String email,String dob,String hireDate,double salary,
        String ssn,int addressID,int job_title_id,int div_ID,String mobilePhone,
        String emergencyContactName,String emergencyContactPhone) {
            this.empID = empID;
            this.fname = fname;
            this.lname = lname;
            this.email = email;
            this.dob = dob;
            this.hireDate = hireDate;
            this.salary = salary;
            this.ssn = ssn;
            this.addressID = addressID;
            this.job_title_id = job_title_id;
            this.div_ID = div_ID;
            this.mobilePhone = mobilePhone;
            this.emergencyContactName = emergencyContactName;
            this.emergencyContactPhone = emergencyContactPhone;
    }

    public int getEmpID() { 
        return empID; 
    }

    public String getFname() { 
        return fname; 
    }

    public String getLname() {  
        return lname; 
    }

    public String getEmail() { 
        return email; 
    }

    public String getDob() { 
        return dob; 
    }

    public String getHireDate() { 
        return hireDate; 
    }

    public double getSalary() { 
        return salary; 
    }

    public String getSSN() {
         return ssn; 
        }

    public int getAddressID() { 
        return addressID; 
    }

    public int getJob_title_id() { 
        return job_title_id; 
    }

    public int getDiv_ID() { 
        return div_ID; 
    }

    public String getMobilePhone() { 
        return mobilePhone; 
    }

    public String getEmergencyContactName() { 
        return emergencyContactName; 
    }

    public String getEmergencyContactPhone() { 
        return emergencyContactPhone; 
    }
}
