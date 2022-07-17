import requests
import json
import os

host_name = os.getenv('ARYTON')
api_key = os.getenv('DEPENDENCY_TRACK_API_KEY')
bom_filepath = os.getenv('BOM_FILE_PATH')
project_name = os.getenv('PROJECT_NAME')

base_uri = f'http://{host_name}:8081/api'
auth_headers = {'X-Api-Key': api_key}


def get_projects():
    response = requests.get(f'{base_uri}/v1/project?excludeInactive=true', headers=auth_headers)
    json_response = response.json()
    return json_response


def get_project_uuid(name):
    projects = get_projects()
    for project in projects:
        if (name == project['name']):
            return project['uuid']
    return ""

def create_project(name):
    project_metadata = {"name": name, "classifier": "APPLICATION", "tags": [], "active": "true"}
    json_metadata = json.dumps(project_metadata)

    headers_ex =  {'Accept': 'application/json, text/plain, */*', 'Content-Type': 'application/json'}
    headers = dict(auth_headers)
    headers.update(headers_ex)

    response = requests.put(f'{base_uri}/v1/project', headers=headers, data=json_metadata)
    if (response.status_code == 201):
        print("project created successfully")
        json_response = response.json()
        return json_response['uuid']
    else:
        print("project exists")
        return ""

def upload_bom(project_uuid, path_to_bom):
    headers_ex =  {'Accept': 'application/json, text/plain, */*'}
    headers = dict(auth_headers)
    headers.update(headers_ex)

    form_data = dict(project = project_uuid, bom = open(path_to_bom, 'rb'))

    response = requests.post(f'{base_uri}/v1/bom', headers=headers, files=form_data)
    if (response.status_code == 200):
        print('BOM updated successfully!')
    else:
        print('Failed to update BOM!')

uuid = get_project_uuid(project_name)
if (uuid == ""):
    print("Creating project!")
    uuid = create_project(project_name)
    if (uuid == ""):
        print("Falied to create project!")
    else:
        print("Uploading BOM")
        upload_bom(uuid, bom_filepath)
else:
    print("Uploading BOM")
    upload_bom(uuid, bom_filepath)