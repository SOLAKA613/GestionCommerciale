package src.com.vente.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import src.com.vente.dao.IDao;
import src.com.vente.entities.Users;

@Service("metierUsers")
public class MetierUsers implements IMetier<Users>{

	@Autowired
	IDao<Users> daoUsers;

	@Override
	public boolean create(Users o) {
		// TODO Auto-generated method stub
		return daoUsers.create(o);
	}

	@Override
	public boolean update(Users o) {
		// TODO Auto-generated method stub
		return daoUsers.update(o);
	}

	@Override
	public boolean delete(Users o) {
		// TODO Auto-generated method stub
		return daoUsers.delete(o);
	}

	@Override
	public Users findById(int id) {
		// TODO Auto-generated method stub
		return daoUsers.findById(id);
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return daoUsers.findAll();
	}
	
	
}
