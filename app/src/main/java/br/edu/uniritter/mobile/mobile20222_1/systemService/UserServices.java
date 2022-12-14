package br.edu.uniritter.mobile.mobile20222_1.systemService;

import android.content.Context;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.User;
import br.edu.uniritter.mobile.mobile20222_1.repository.UserRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.UserRepositoryInterface;
import br.edu.uniritter.mobile.mobile20222_1.repository.UserSQLRepository;

public class UserServices {
    public static final int REST_REPO = 0;
    public static final int SQLITE_REPO = 1;
    public static final int DUAL_REPO_REST = 9;
    public static final int DUAL_REPO_SQL = 8;

    private UserRepositoryInterface repository;
    private UserRepositoryInterface repositoryAux;
    private int type;

    public UserServices(int type, Context context) {
        this.type = type;
        switch (type) {
            case 0 : {this.repository = UserRepository.getInstance(context, null); break;}
            case 1 : {this.repository = UserSQLRepository.getInstance(context); break;}
            case 8 : {this.repository = UserSQLRepository.getInstance(context);
                      this.repositoryAux = UserRepository.getInstance(context, null);
                      break;}
            case 9 : {this.repositoryAux = UserSQLRepository.getInstance(context);
                      this.repository = UserRepository.getInstance(context, null);
                      break;}
            default: ;

        }
    }

    public List<User> getUsers(){
        List<User> list = repository.getUsers();
        if (list.size() == 0) {
            if (repositoryAux != null) {
                list = repositoryAux.getUsers();
            }
        }
        return list;
    };
    public User getUserById(int id){
        return repository.getUserById(id);
    }
    public User getUserByUserLogin(String login){
        return getUserByUserLogin(login);
    }
    public List<User> getUsersByName(String name) {
        return repository.getUsersByName(name);
    }
    public User addUser(User user){
        return repository.addUser(user);
    }
    public User updateUser(User user) {
        return repository.updateUser(user);
    }
    public User removeUser(User user) {
        return repository.removeUser(user);
    }


}
