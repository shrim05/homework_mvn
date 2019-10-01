package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BuyerDAOImpl implements IBuyerDAO{
	private SqlSessionFactory sqlSessionFactory;
	private static BuyerDAOImpl instance;
	
	private BuyerDAOImpl() {
		sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	}

	public static BuyerDAOImpl getInstance() {
		if(instance==null) {
			instance = new BuyerDAOImpl();
		}
		return instance;
	}
	
	@Override
	public int insertBuyer(BuyerVO bv) {
		try(
						SqlSession sqlSession = sqlSessionFactory.openSession();
						){
					IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
					int cnt = mapper.insertBuyer(bv);
					sqlSession.commit();
					return cnt;
				}
	}
	
	@Override
	public int selectBuyerCount(PagingInfoVO<BuyerVO> paging) {
		try(
						SqlSession sqlSession = sqlSessionFactory.openSession();
						){
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
					return mapper.selectBuyerCount(paging);
				}
	}
	
	
	@Override
	public List<BuyerVO> selectBuyerList(PagingInfoVO pagingVO) {
		try(
						SqlSession sqlSession = sqlSessionFactory.openSession();
						){
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
					return mapper.selectBuyerList(pagingVO);
				}
	}

	@Override
	public BuyerVO selectBuyer(BuyerVO bv) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
			return mapper.selectBuyer(bv);
		}
	}

	@Override
	public int updateBuyer(BuyerVO bv) {
		try(
						SqlSession sqlSession = sqlSessionFactory.openSession();
						){
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
				int cnt = mapper.updateBuyer(bv);
				sqlSession.commit();
					return cnt;
				}
	}

	@Override
	public int deleteBuyer(BuyerVO bv) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
			int cnt = mapper.deleteBuyer(bv);
			sqlSession.commit();
			return cnt;
		}
	}

	

}
