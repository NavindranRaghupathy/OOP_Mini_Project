class Services extends Product{
   // private String Sdescription;
    private String duration;

    public Services(String p_id,String p_name,String description,float price,
                    int quantity_available,String date_added,String brand,
                    String duration){
         super(p_id,p_name,description,price,quantity_available,date_added,brand);
        this.duration = duration;
    }

    public void setDuration(String duration){this.duration = duration;}

    public String getDuration(){return duration;}

    public void printDetails(){
        super.printDetails();
        System.out.println("Durations: "+duration);
    }
}