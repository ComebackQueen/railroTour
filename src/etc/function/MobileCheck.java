package etc.function;

import javax.servlet.http.HttpServletRequest;

public class MobileCheck {
	 public static boolean isMobile(HttpServletRequest request) {
			String userAgent = request.getHeader("user-agent");
			
			//System.out.println("현재들어온 유저:"+userAgent);
			boolean mobile1 = userAgent.matches(".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
			boolean mobile2 = userAgent.matches(".*(LG|SAMSUNG|Samsung).*");
			if(mobile1 || mobile2){
				return true;
			}
			return false;
		}
}
