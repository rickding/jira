import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.IssueManager
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.fields.CustomField
import org.apache.log4j.Level
import org.apache.log4j.Logger

log.setLevel(Level.INFO)

// 读取issue
String issueKey = "DEMO-1"
IssueManager issueMgr = ComponentAccessor.getIssueManager()
Issue issue = issueMgr.getIssueObject(issueKey)
assert issue

String msg = String.format("Hello world, %s, %s, %s", issueKey, issue?.key, issue?.getSummary())
log.info(msg)
return msg
