import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.index.IssueIndexManager
import com.atlassian.jira.issue.Issue
import org.apache.log4j.Level
import org.apache.log4j.Logger

log.setLevel(Level.INFO)

// 测试时读取issue
//String issueKey = "DEMO-1"
//Issue issue = ComponentAccessor.getIssueManager().getIssueObject(issueKey)

assert issue
log.info String.format("%s, %s, %s", issue, issue.issueType.name, issue.status.name)

/**
 * 和日期有关的字段每天早上主动触发更新计算: 0 0 4 * * ?
 * JQL: dueDate is not EMPTY
 */
IssueIndexManager issueIndexManager = ComponentAccessor.getComponent(IssueIndexManager.class)
issueIndexManager.reIndex(issue)
