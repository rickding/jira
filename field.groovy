import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.fields.CustomField
import org.apache.log4j.Level
import org.apache.log4j.Logger

log.setLevel(Level.INFO)

// 测试时读取issue
//String issueKey = "DEMO-1"
//Issue issue = ComponentAccessor.getIssueManager().getIssueObject(issueKey)

assert issue
log.info String.format("%s, %s, %s, %s", issue, issue.projectObject.key, issue.issueType.name, issue.status.name)

// 某个项目下的某类单据
if (issue.projectObject.key != "DEMO" || issue.issueType.name != "Story" || issue.status.name == "Done" || !issue.dueDate) {
    return null
}

Date date = issue.dueDate
if (!date) {
    // 读取字段
    String fieldName = "StartDate"
    CustomField customField = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName(fieldName)
    date = (Date) issue.getCustomFieldValue(customField)
}

if (!date) {
    return null
}

return (int) ((new Date().getTime() - date.getTime()) / 1000 / 3600 / 24)
