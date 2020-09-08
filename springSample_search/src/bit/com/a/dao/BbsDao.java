package bit.com.a.dao;

import java.util.List;

import bit.com.a.dto.BbsDto;
import bit.com.a.dto.BbsParam;

public interface BbsDao {

	List<BbsDto> getBbsList();	
	
	boolean writeBbs(BbsDto dto);
	
	BbsDto getBbs(int seq);
	void readCount(int seq);
	
	boolean replyBbsUpdate(BbsDto bbs);
	boolean replyBbsInsert(BbsDto bbs);
	
	List<BbsDto> getBbsList(BbsParam bbs);	
	
}
