import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.fields.CustomField
import org.apache.log4j.Level
import org.apache.log4j.Logger

log.setLevel(Level.INFO)

// 测试时读取issue
//String issueKey = "DEMO-1"
//Issue issue = ComponentAccessor.getIssueManager().getIssueObject(issueKey)
//assert issue
//log.info String.format("%s, %s, %s", issue, issue.issueType.name, issue.status.name)

//String fieldName = "Story Points"
//CustomField customField = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName(fieldName)
//String fieldValue = (String)issue.getCustomFieldValue(customField)

/**
 * 在新建单据提交到数据库之前校验数据
 */
Double fieldValue = (Double) cfValues["Story Points"]
log.info String.format("value: %s", fieldValue)
if (fieldValue == null) {
    return true
}

// 示例逻辑：数据可以不填，但是只允许某个权限组的用户填写
ApplicationUser user = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser()
if (!ComponentAccessor.getGroupManager().isUserInGroup(user, "story_point_group")) {
    return false
}

// 请输入正确的价值，范围1-100
Double value = Double.valueOf(fieldValue)
return value && value >= 1 && value <= 100
