package org.carry;

import com.alibaba.fastjson.JSON;
import org.carry.dto.GithubUser;

/**
 * @Author: CARRY
 * @CreateTime: 2019-06-14 23:15
 * @Description: ${Description}
 */
public class test {
    public static void main(String[] args) {
        String s = "{\"login\":\"XueKX\",\"id\":25972966,\"node_id\":\"MDQ6VXNlcjI1OTcyOTY2\",\"avatar_url\":\"https://avatars1.githubusercontent.com/u/25972966?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/XueKX\",\"html_url\":\"https://github.com/XueKX\",\"followers_url\":\"https://api.github.com/users/XueKX/followers\",\"following_url\":\"https://api.github.com/users/XueKX/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/XueKX/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/XueKX/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/XueKX/subscriptions\",\"organizations_url\":\"https://api.github.com/users/XueKX/orgs\",\"repos_url\":\"https://api.github.com/users/XueKX/repos\",\"events_url\":\"https://api.github.com/users/XueKX/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/XueKX/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":\"Carry在Carry\",\"company\":null,\"blog\":\"\",\"location\":\"NanJing\",\"email\":null,\"hireable\":null,\"bio\":null,\"public_repos\":5,\"public_gists\":0,\"followers\":1,\"following\":9,\"created_at\":\"2017-02-23T05:41:46Z\",\"updated_at\":\"2019-06-14T14:53:18Z\",\"private_gists\":0,\"total_private_repos\":3,\"owned_private_repos\":3,\"disk_usage\":22440,\"collaborators\":0,\"two_factor_authentication\":false,\"plan\":{\"name\":\"free\",\"space\":976562499,\"collaborators\":0,\"private_repos\":10000}}";
        GithubUser githubUser = JSON.parseObject(s, GithubUser.class);
        System.out.println(githubUser.getName());
    }
}
