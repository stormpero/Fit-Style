if($(Get-ExecutionPolicy) -ne 'AllSigned') {
	echo 'Not all signed!'
}
else {
	echo 'All signed!'
}

Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))