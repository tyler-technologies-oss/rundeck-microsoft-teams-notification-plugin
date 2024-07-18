import com.dtolabs.rundeck.plugins.notification.NotificationPlugin
import groovy.json.JsonOutput


rundeckPlugin(NotificationPlugin){
    title="Microsoft Teams notification via PowerAutomate Plugin"
    description="Allows to set up notification for Microsoft Teams chats for a channel, via a PowerAutomate Webhook URL. To use it you will have to obtain webhook for your channel first."

    configuration {
        webhook_url title:"Webhook URL", required: true, type:"String", description:"PowerAutomate Teams webhook for creating an AdaptiveCard"
    }

    onstart {
        type = "START"
        color = "accent"
        json_payload = JsonOutput.toJson([
            "type": "message",
            "attachments": [
                [
                    "contentType": "application/vnd.microsoft.card.adaptive",
                    "contentUrl": null,
                    "content": [
                        "type": "AdaptiveCard",
                        "msteams": [
                            "width": "Full"
                        ],
                        "body": [
                            [
                                "type": "Container",
                                "items": [
                                    [
                                        "type": "TextBlock",
                                        "text": "[${type}] Rundeck Job - ${execution.project} : ${execution.job.group} : ${execution.job.name}",
                                        "weight": "bolder",
                                        "size": "medium",
                                        "color": "${color}"
                                    ],
                                    [
                                        "type": "FactSet",
                                        "facts": [
                                            [
                                                "title": "Description",
                                                "value": "${execution.job.description}"
                                            ],
                                            [
                                                "title": "Job Id",
                                                "value": "${execution.job.id}"
                                            ],
                                            [
                                                "title": "Execution Id",
                                                "value": "${execution.id}"
                                            ],
                                            [
                                                "title": "Status",
                                                "value": "${execution.status}"
                                            ],
                                            [
                                                "title": "Started At",
                                                "value": "${execution.dateStarted}"
                                            ]
                                        ]
                                    ]
                                ]
                            ]
                        ],
                        "actions": [
                            [
                                "type": "Action.OpenUrl",
                                "title": "View in Rundeck",
                                "url": "${execution.href}"
                            ]
                        ],
                        '$schema': "http://adaptivecards.io/schemas/adaptive-card.json",
                        "version": "1.3"
                    ]
                ]
            ]
        ])
        process = [ 'curl', '-v', '-k', '-X', 'POST', '-H', 'Content-Type: application/json', '-d', "${json_payload}", "${configuration.webhook_url}" ].execute().text
        return true
    }

    onfailure {
        type = "FAILURE"
        color = "attention"
        json_payload = JsonOutput.toJson([
            "type": "message",
            "attachments": [
                [
                    "contentType": "application/vnd.microsoft.card.adaptive",
                    "contentUrl": null,
                    "content": [
                        "type": "AdaptiveCard",
                        "msteams": [
                            "width": "Full"
                        ],
                        "body": [
                            [
                                "type": "Container",
                                "items": [
                                    [
                                        "type": "TextBlock",
                                        "text": "[${type}] Rundeck Job - ${execution.project} : ${execution.job.group} : ${execution.job.name}",
                                        "weight": "bolder",
                                        "size": "medium",
                                        "color": "${color}"
                                    ],
                                    [
                                        "type": "FactSet",
                                        "facts": [
                                            [
                                                "title": "Description",
                                                "value": "${execution.job.description}"
                                            ],
                                            [
                                                "title": "Job Id",
                                                "value": "${execution.job.id}"
                                            ],
                                            [
                                                "title": "Execution Id",
                                                "value": "${execution.id}"
                                            ],
                                            [
                                                "title": "Status",
                                                "value": "${execution.status}"
                                            ],
                                            [
                                                "title": "Started At",
                                                "value": "${execution.dateStarted}"
                                            ]
                                        ]
                                    ]
                                ]
                            ]
                        ],
                        "actions": [
                            [
                                "type": "Action.OpenUrl",
                                "title": "View in Rundeck",
                                "url": "${execution.href}"
                            ]
                        ],
                        '$schema': "http://adaptivecards.io/schemas/adaptive-card.json",
                        "version": "1.3"
                    ]
                ]
            ]
        ])
        process = [ 'curl', '-v', '-k', '-X', 'POST', '-H', 'Content-Type: application/json', '-d', "${json_payload}", "${configuration.webhook_url}" ].execute().text
        return true
    }

    onsuccess {
        type = "SUCCESS"
        color = "good"
        json_payload = JsonOutput.toJson([
            "type": "message",
            "attachments": [
                [
                    "contentType": "application/vnd.microsoft.card.adaptive",
                    "contentUrl": null,
                    "content": [
                        "type": "AdaptiveCard",
                        "msteams": [
                            "width": "Full"
                        ],
                        "body": [
                            [
                                "type": "Container",
                                "items": [
                                    [
                                        "type": "TextBlock",
                                        "text": "[${type}] Rundeck Job - ${execution.project} : ${execution.job.group} : ${execution.job.name}",
                                        "weight": "bolder",
                                        "size": "medium",
                                        "color": "${color}"
                                    ],
                                    [
                                        "type": "FactSet",
                                        "facts": [
                                            [
                                                "title": "Description",
                                                "value": "${execution.job.description}"
                                            ],
                                            [
                                                "title": "Job Id",
                                                "value": "${execution.job.id}"
                                            ],
                                            [
                                                "title": "Execution Id",
                                                "value": "${execution.id}"
                                            ],
                                            [
                                                "title": "Status",
                                                "value": "${execution.status}"
                                            ],
                                            [
                                                "title": "Started At",
                                                "value": "${execution.dateStarted}"
                                            ]
                                        ]
                                    ]
                                ]
                            ]
                        ],
                        "actions": [
                            [
                                "type": "Action.OpenUrl",
                                "title": "View in Rundeck",
                                "url": "${execution.href}"
                            ]
                        ],
                        '$schema': "http://adaptivecards.io/schemas/adaptive-card.json",
                        "version": "1.3"
                    ]
                ]
            ]
        ])
        process = [ 'curl', '-v', '-k', '-X', 'POST', '-H', 'Content-Type: application/json', '-d', "${json_payload}", "${configuration.webhook_url}" ].execute().text
        return true
    }
}
