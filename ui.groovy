import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue
import com.atlassian.plugin.web.Condition
import com.atlassian.jira.plugin.webfragment.model.JiraHelper
import org.apache.log4j.Level
import org.apache.log4j.Logger

log.setLevel(Level.INFO)

// 测试时读取issue
//String issueKey = "DEMO-1"
//Issue issue = ComponentAccessor.getIssueManager().getIssueObject(issueKey)

assert issue
log.info String.format("%s, %s, %s, %s", issue, issue.projectObject.key, issue.issueType.name, issue.status.name)

/**
 * 某个项目下的某类单据显示打印按钮，请求头Referer中包含issue key
 * - Custom web item, operations-top-level, weight: 1
 * - Link: http://jext.top
 */
class Check implements Condition {
    void init(Map<String,String> params) {
    }

    boolean	shouldDisplay(Map<String,Object> context) {
        Issue issue = (Issue) context.get("issue")
        return issue && issue.projectObject.key == "DEMO" && issue.issueType.name == "Story"
    }
}

// 不实现Condition而是直接返回true/false
//return issue.projectObject.key == "DEMO" && issue.issueType.name == "Story"
