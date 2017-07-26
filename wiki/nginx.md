
        location / {
            root   html;
            index  index.html index.htm;
        }

        location /meta {
            try_files $uri $uri/ @fallback;
            alias   /Users/wanghg/Desktop/projects/gitlab/meta/src/main/webapp;
            index  index.html index.htm;
        }

        location @fallback {
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP  $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

            proxy_pass http://localhost:8089;
        }
