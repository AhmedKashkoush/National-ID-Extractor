package nationalid;

public class IDInfo {

    private String year;
    private String month;
    private String day;
    private String gov;
    private String gender;

    private String[] govList = {"Cairo", "Alex", "Port Said", "Suez","","","","","","", "Damietta", "Dakahlia", "Sharkia", "Qalyubi", "Kafr El-Sheikh", "Gharbiya", "Menoufia", "Beheira", "Ismailia","" ,"Giza", "Bani Sweif", "Fayoum", "Minya", "Asyut", "Sohag", "Qena", "Aswan", "Luxor","", "Red Sea", "New Valley", "Marsa Matroh", "North Sinai", "South of Sinai", "Outside Egypt"};

    public String getDate() {
        String date = this.year + '/' + this.month + '/' + this.day;
        if (this.year == null || this.month == null || this.day == null) {
            return "No Date Available";
        }
        return date;
    }

    public String getGov() {
        if (this.gov == null) {
            return "No Governorate Available";
        }
        return this.gov;
    }

    public String getGender() {
        if (this.gender == null) {
            return "No Gender Available";
        }
        return this.gender;
    }

    public void extractInfo(String id) {
        try {
            String yearStr = "";
            String monthStr = "";
            String dayStr = "";
            String govCode = "";
            if (id.length() < 14) {
                throw new Exception("National ID Must Be 14 Numbers");
            }
            for (int i = 0; i < id.length(); i++) {
                Integer.parseInt(String.valueOf(id.charAt(i)));
            }
            int centuryNum = Integer.parseInt(String.valueOf(id.charAt(0)));
            int yearNum = (17 + centuryNum) * 100;
            for (int i = 1; i < 9; i++) {
                if (i < 3) {
                    yearStr += id.charAt(i);
                } else if (i < 5) {
                    monthStr += id.charAt(i);
                } else if (i < 7) {
                    dayStr += id.charAt(i);
                } else {
                    govCode += id.charAt(i);
                }
            }

            int yearInt = yearNum + Integer.parseInt(yearStr);
            this.year = Integer.toString(yearInt);
            this.month = monthStr;
            this.day = dayStr;

            if (Integer.parseInt(this.month) > 12 || Integer.parseInt(this.month) < 1) {
                this.year = null;
                this.month = null;
                this.day = null;
                throw new Exception("Invalid Month Format");
            }
            if (Integer.parseInt(this.day) > 31 || Integer.parseInt(this.day) < 1) {
                this.year = null;
                this.month = null;
                this.day = null;
                throw new Exception("Invalid Day Format");
            }
            int code = Integer.parseInt(govCode);
            if (code < 1 || (code > 4 && code < 11) || code == 20 || code == 30) {
                this.year = null;
                this.month = null;
                this.day = null;
                this.gov = null;
                throw new Exception("Invalid Gov Code");
            }
            this.gov = govList[code - 1];
            int genderNum = Integer.parseInt(String.valueOf(id.charAt(id.length() - 2)));
            this.gender = genderNum % 2 == 0 ? "Female" : "Male";
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
