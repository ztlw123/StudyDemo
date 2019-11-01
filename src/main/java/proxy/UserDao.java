package proxy;

/**
 * @Author zjh
 * @Date 2019/04/26,09:30
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("save obj");
    }

    @Override
    public void find() {
        System.out.println("find obj");
    }
}
