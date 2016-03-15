package bean;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ShowAllVotePageDao {
	public List<VoteListInfo> pageQuery(int pageSize,int pageNow,List<VoteListInfo> objectlist){
		List<VoteListInfo> list=new ArrayList<VoteListInfo>();
		int temp=0;
		temp=pageNow*pageSize;
		if(temp>objectlist.size())
		{	
			temp=objectlist.size();
			}
		for(int i=pageNow*pageSize-pageSize;i<temp;i++)
		{
			
			list.add(objectlist.get(i));
		}
		return list;
	}
		public int pageTotle(int pageSize, int pageNow) {
			int i=0;
			Configuration config=new Configuration().configure();
			SessionFactory sessionFactory=config.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			i = session.createQuery("from Voteinfo").list().size();
			i = i % pageSize == 0 ? i / pageSize : i / pageSize + 1;
			tx.commit();
			session.close();
			return i;
			
		}
	

}
