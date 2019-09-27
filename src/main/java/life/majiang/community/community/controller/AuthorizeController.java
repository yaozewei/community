package life.majiang.community.community.controller;

import life.majiang.community.community.dto.AccessTokenDTO;
import life.majiang.community.community.dto.GithubUser;
import life.majiang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secert}")
    private String clientSeceret;

    @Value("${github.redirect.uri}")
    private String redirectUri;


    @GetMapping("/callback")
    public String authorize(@RequestParam(name = "code")String code,
                            @RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSeceret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessTokenDTO(accessTokenDTO);
        GithubUser user  = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return  "index";
    }
}
