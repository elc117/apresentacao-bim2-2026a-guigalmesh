class TeamMate {
    private String userId;
    private String name;
    private Boolean online;

    public TeamMate(){
        this.userId = "default";
        this.name = "default";
        this.online = false;
    }

    public TeamMate(String userId, String name, Boolean online){
        setUserId(userId);
        setName(name);
        setOnline(online);
    }

    public TeamMate(String userId, String name){
        setUserId(userId);
        setName(name);
        setOnline(false);
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setOnline(Boolean online){
        this.online = online;
    }

    public String getName(){
        return this.name;
    }

    public Boolean getOnline(){
        return this.online;
    }

    public String getId(){
        return this.userId;
    }
}
