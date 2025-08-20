def call(env, region = "cn") {
    def configs = [
        "cn": [
            "dev": [
                registry: "registry.cn-hangzhou.aliyuncs.com",
                secret_name: "aliyun-hub-cn",
                docker_registry_auth: "xxxx-xxxx-cn-dev",
                git_auth: "xxxx-xxxx-cn-dev",
                k8s_auth: "xxxx-xxxx-cn-dev",
                k8s_cli: "xxxx-xxxx-cn-dev"
            ],
            "prod": [
                registry: "registry.cn-hangzhou.aliyuncs.com",
                secret_name: "aliyun-hub-cn",
                docker_registry_auth: "xxxx-xxxx-cn-prod",
                git_auth: "xxxx-xxxx-cn-prod",
                k8s_auth: "xxxx-xxxx-cn-prod",
                k8s_cli: "xxxx-xxxx-cn-prod"
            ]
        ],
        "sg": [
            "prod": [
                registry: "registry.ap-southeast-1.aliyuncs.com",
                secret_name: "aliyun-hub-sg",
                docker_registry_auth: "xxxx-xxxx-sg-prod",
                git_auth: "xxxx-xxxx-sg-prod",
                k8s_auth: "xxxx-xxxx-sg-prod",
                k8s_cli: "xxxx-xxxx-sg-prod"
            ]
        ],
        "ae": [
            "dev": [
                registry: "registry.me-east-1.aliyuncs.com",
                secret_name: "aliyun-hub-ae",
                docker_registry_auth: "xxxx-xxxx-ae-dev",
                git_auth: "xxxx-xxxx-ae-dev",
                k8s_auth: "xxxx-xxxx-ae-dev",
                k8s_cli: "xxxx-xxxx-ae-dev"
            ]
        ]
    ]

    if (!configs.containsKey(region)) {
        error("未找到地区配置: ${region}")
    }
    if (!configs[region].containsKey(env)) {
        error("未找到环境配置: ${region}-${env}")
    }

    // 动态加上 namespace = env
    def cfg = configs[region][env]
    cfg.namespace = env
    return cfg
}
