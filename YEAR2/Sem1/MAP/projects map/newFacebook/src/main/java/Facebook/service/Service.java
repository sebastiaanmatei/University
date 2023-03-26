package Facebook.service;

import Facebook.domain.Friendship;
import Facebook.domain.User;
import Facebook.domain.validators.Validator;
import Facebook.exceptions.RepositoryException;
import Facebook.repo.Repository;
import Facebook.utils.events.ChangeEventType;
import Facebook.utils.events.UserEntityChangeEvent;
import Facebook.utils.observer.Observable;
import Facebook.utils.observer.Observer;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

public class Service<ID> implements Observable<UserEntityChangeEvent> {
    private Repository<Integer, User> usrepo;
    private Repository<Integer, Friendship> frepo;
    private Validator<User> validator;
    private List<Observer<UserEntityChangeEvent>> observers=new ArrayList<>();

    int[][] network = new int[10000][10000];

    public Service(Repository<Integer, User> repo, Repository<Integer, Friendship> repoFriendships, Validator validator) throws FileNotFoundException {
        this.usrepo = repo;
        this.frepo = repoFriendships;
        this.validator = validator;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                network[i][j] = 0;
            }
        }
    }


    @Override
    public void addObserver(Observer<UserEntityChangeEvent> e) {
        observers.add(e);

    }

    @Override
    public void removeObserver(Observer<UserEntityChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(UserEntityChangeEvent t) {

        observers.stream().forEach(x->x.update(t));
    }


    public User addUser(int id, String name, int age, String sex, String city, String password) {
        boolean verified=true;

        for(User u:getListUsers()){
            if(u.getUserID()==id){
                verified=false;
                System.out.println("user id already exists!");
            }
        }
        if(verified){
            User user = new User(id, name, age, sex, city, password);


//            if(usrepo.save(user).isEmpty()){
//                UserEntityChangeEvent event = new UserEntityChangeEvent(ChangeEventType.ADD, user);
//                notifyObservers(event);
//                return null;
//            }
//            return user;
            usrepo.save(user);


        }

        return null;
    }

    public User removeUser(User user) {

        int id=user.getUserID();
        for(Friendship f:getListFriendships()){

            if(f.getU1()==id || f.getU2()==id){
                try {
                    removeFriendship(f.getU1(), f.getU2());
                    removeFriendship(f.getU2(), f.getU1());
                    User us1=getUserByID(f.getU1());
                    User us2=getUserByID(f.getU2());

                    if(us1!=null || us2!=null){
                        us1.removeFriend(us2);
                        us2.removeFriend(us1);
                    }


                } catch (RepositoryException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        Optional<User> usr= Optional.ofNullable(usrepo.delete(id));
        if (usr.isPresent()) {
            notifyObservers(new UserEntityChangeEvent(ChangeEventType.DELETE, usr.get()));
            return usr.get();}
        return null;

        //usrepo.delete(id);

    }

    public void addFriend(int u1, int u2) {
        System.out.println("asdasd");
        network[u1][u2] = 1;
        network[u2][u1] = 1;
        int verified=0;
        User us1 = usrepo.findOne(u1);
        User us2 = usrepo.findOne(u2);

        LocalDateTime now = LocalDateTime.now();
        String date=now.toString();
        for(Friendship u:frepo.findAll()){
            if(u.getU1()==u1 && u.getU2()==u2 || u.getU1()==u2 && u.getU2()==u1){
                verified=1;
                break;
            }
        }
        if(verified==0){
            System.out.println("adauga");
            if(us1!=null && us2!=null){
                us1.addFriend(us2);
                us2.addFriend(us1);
            }
            Friendship fr=new Friendship(u1, u2, date);
            Random rand = new Random(); //instance of random class
            int upperbound = 10000;
            int id=rand.nextInt(upperbound);
            fr.setId(id);
            frepo.save(fr);
        }else{
            System.out.println("friendship already exists");
        }


    }

    public void removeFriendship(int u1, int u2) throws RepositoryException {
        network[u1][u2] = 0;
        network[u2][u1] = 0;

        User us1=getUserByID(u1);
        User us2=getUserByID(u2);
        List<Friendship> list=getListFriendships();
        for(Friendship f:list){
            if(f.getU1().equals(u1) && f.getU2().equals(u2) || f.getU1().equals(u2) && f.getU2().equals(u1)){
                System.out.println(f.getId());
                frepo.delete(f.getId());

            }
        }
        if(us1!=null && us2!=null){
            us1.removeFriend(us2);
            us2.removeFriend(us1);
        }

    }

    public List<User> getListUsers() {
        List<User> list=new ArrayList((Collection) usrepo.findAll());
        return list;
    }

    public List<Friendship> getListFriendships() {
        List<Friendship> list=new ArrayList((Collection) frepo.findAll());
        return list;
    }

    public void makeFriendships(){

        for(Friendship f:getListFriendships()){
            int u1=f.getU1();
            int u2=f.getU2();
            network[u1][u2]=1;
            network[u2][u1]=1;
            User us1=getUserByID(u1);
            User us2=getUserByID(u2);
            if(us1!=null && us2!=null){
                us1.addFriend(us2);
                us2.addFriend(us1);

            }else{
                System.out.println("wrong ID's"+u1+" "+u2);
            }


        }
    }

    public User getUserByID(int id) {
        return usrepo.findOne(id);
    }

    public List<User> getFriends(int id) {

        User us = getUserByID(id);
        if(us!=null){
            return us.getFriends();
        }
        return null;


    }

    public int maxID() {
        int maxd = 0;
        List<User> usr = getListUsers();
        for (User u : usr) {
            if (u.getUserID() > maxd) {
                System.out.println(maxd+" "+u.getUserID());
                maxd = (int)u.getUserID();
            }
        }
        return maxd;
    }

    boolean hasFriends(int id) {
        for (int i = 1; i <= 999; i++) {
            if (network[i][id] == 1) {
                return true;
            }
        }
        return false;

    }

    public void DFSUtil(List<Integer>[] adj, int v, boolean[] visited, int count) {
        visited[v] = true;
        //int ok=1;

        for (int n : adj[v]) {
            if (!visited[n]) {
                //ok=0;
                DFSUtil(adj, n, visited, count);

            }
        }
    }



    public int howManyComunities() {

        int max = maxID();
        List<Integer>[] adj;
        int size = getListUsers().size() + 1;
        adj = new LinkedList[max];
        for (int i = 0; i < max; ++i)
            adj[i] = new LinkedList();


        System.out.println(max);
        int count = 0;

        int[][] netw = network;

        for (int i = 1; i < 10000; i++) {
            for (int j = 1; j < 10000; j++) {
                if (netw[i][j] == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }

        boolean[] visited = new boolean[size + 1];
        int maxCom = 0;

        for (int i = 0; i < size; ++i) {
            if (!visited[i] && hasFriends(i)) {
                count++;
                int z = 0;
                System.out.println(i);
                DFSUtil(adj, i, visited, z);
            }

        }

        return count;
    }

    public void maxComm() {

        int[] visitedd=new int[1000];
        Graph.Graph g=new Graph.Graph(network, visitedd);
        int[] idd=g.longestComponent();

        for(int i=0;i<idd.length;i++){
            System.out.println(getUserByID(idd[i]).getUsername());
        }


    }

}
