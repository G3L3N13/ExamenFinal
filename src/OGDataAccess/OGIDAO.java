package OGDataAccess;

import java.util.List;

public interface OGIDAO<T> {

    public boolean ogCreate(T entity) throws Exception;

    public List<T> ogReadAll() throws Exception;

    public boolean ogUpdate(T entity) throws Exception;

    public boolean ogDelete(int id) throws Exception;

    public T ogReadBy(Integer id) throws Exception;

}
