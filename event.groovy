import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.event.issue.IssueEvent
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.event.type.EventDispatchOption
import org.apache.log4j.Level
import org.apache.log4j.Logger

log.setLevel(Level.INFO)

assert event
log.info String.format("event: %d, %s", event.eventTypeId, event)
Issue issue = event.issue

// 测试时读取issue
//String issueKey = "DEMO-1"
//Issue issue = ComponentAccessor.getIssueManager().getIssueObject(issueKey)

assert issue
log.info String.format("%s, %s, %s, %s", issue, issue.projectObject.key, issue.issueType.name, issue.status.name)

// 事件发生时做一些处理，比如设置或清空某些字段
["Story Points", "custom field"].each {
    CustomField customField = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName((String) it)
    issue.setCustomFieldValue(customField, null)
}

// 脚本修改字段后主动触发更新缓存
ComponentAccessor.getIssueManager().updateIssue(
        ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
        issue,
        EventDispatchOption.DO_NOT_DISPATCH,
        false
)
