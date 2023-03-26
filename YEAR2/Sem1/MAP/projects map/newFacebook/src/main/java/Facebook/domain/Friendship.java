package Facebook.domain;

public class Friendship extends Entity<Integer> {
    //    private final User u1;
//    private final User u2;
    private int u1;
    private int u2;
    //private int friendshipID;
    private String friendsFrom;


    public Friendship(int u1, int u2, String friendsFrom) {
        this.u1 = u1;
        this.u2 = u2;
        this.friendsFrom = friendsFrom;
    }

    public Integer getU1() {
        return u1;
    }

    public Integer getU2() {
        return u2;
    }

    public Integer getIdFr() {
        return getId();
    }

    public void setId(int id){
        super.setId(id);
    }
    public String getFriendsFrom() {
        return friendsFrom;
    }


}
