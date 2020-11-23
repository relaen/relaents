import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {User} from './user'
import {FuncCompany} from './funccompany'
import {Agent} from './agent'

@Entity("t_member",'tunnel')
export class Member extends BaseEntity{
	@Id()
	@ManyToOne({entity:'User',eager:false})
	@JoinColumn({name:'user_id',refName:'user_id'})
	private user:User;

	@ManyToOne({entity:'FuncCompany',eager:false})
	@JoinColumn({name:'func_company_id',refName:'func_company_id'})
	private funcCompany:FuncCompany;

	@ManyToOne({entity:'Agent',eager:false})
	@JoinColumn({name:'agent_id',refName:'agent_id'})
	private agent:Agent;

	@Column({
		name:'user_name',
		type:'string',
		nullable:true
	})
	private userName:string;

	@Column({
		name:'password',
		type:'string',
		nullable:true
	})
	private password:string;

	@Column({
		name:'head_img',
		type:'string',
		nullable:true
	})
	private headImg:string;

	@Column({
		name:'nick_name',
		type:'string',
		nullable:true
	})
	private nickName:string;

	@Column({
		name:'birthday',
		type:'string',
		nullable:true
	})
	private birthday:string;

	@Column({
		name:'sex',
		type:'int',
		nullable:true
	})
	private sex:number;

	@Column({
		name:'email',
		type:'string',
		nullable:true
	})
	private email:string;

	@Column({
		name:'openid',
		type:'string',
		nullable:true
	})
	private openid:string;

	@Column({
		name:'real_name',
		type:'string',
		nullable:true
	})
	private realName:string;

	@Column({
		name:'id_no',
		type:'string',
		nullable:true
	})
	private idNo:string;

	@Column({
		name:'idno_imgs',
		type:'string',
		nullable:true
	})
	private idnoImgs:string;

	@Column({
		name:'regist_time',
		type:'int',
		nullable:true
	})
	private registTime:number;

	@Column({
		name:'mobile',
		type:'string',
		nullable:true
	})
	private mobile:string;

	@Column({
		name:'tel',
		type:'string',
		nullable:true
	})
	private tel:string;

	@Column({
		name:'enabled',
		type:'int',
		nullable:true
	})
	private enabled:number;

	public getUser():User{
		return this.user;
	}
	public setUser(value:User){
		this.user = value;
	}

	public getFuncCompany():FuncCompany{
		return this.funcCompany;
	}
	public setFuncCompany(value:FuncCompany){
		this.funcCompany = value;
	}

	public getAgent():Agent{
		return this.agent;
	}
	public setAgent(value:Agent){
		this.agent = value;
	}

	public getUserName():string{
		return this.userName;
	}
	public setUserName(value:string){
		this.userName = value;
	}

	public getPassword():string{
		return this.password;
	}
	public setPassword(value:string){
		this.password = value;
	}

	public getHeadImg():string{
		return this.headImg;
	}
	public setHeadImg(value:string){
		this.headImg = value;
	}

	public getNickName():string{
		return this.nickName;
	}
	public setNickName(value:string){
		this.nickName = value;
	}

	public getBirthday():string{
		return this.birthday;
	}
	public setBirthday(value:string){
		this.birthday = value;
	}

	public getSex():number{
		return this.sex;
	}
	public setSex(value:number){
		this.sex = value;
	}

	public getEmail():string{
		return this.email;
	}
	public setEmail(value:string){
		this.email = value;
	}

	public getOpenid():string{
		return this.openid;
	}
	public setOpenid(value:string){
		this.openid = value;
	}

	public getRealName():string{
		return this.realName;
	}
	public setRealName(value:string){
		this.realName = value;
	}

	public getIdNo():string{
		return this.idNo;
	}
	public setIdNo(value:string){
		this.idNo = value;
	}

	public getIdnoImgs():string{
		return this.idnoImgs;
	}
	public setIdnoImgs(value:string){
		this.idnoImgs = value;
	}

	public getRegistTime():number{
		return this.registTime;
	}
	public setRegistTime(value:number){
		this.registTime = value;
	}

	public getMobile():string{
		return this.mobile;
	}
	public setMobile(value:string){
		this.mobile = value;
	}

	public getTel():string{
		return this.tel;
	}
	public setTel(value:string){
		this.tel = value;
	}

	public getEnabled():number{
		return this.enabled;
	}
	public setEnabled(value:number){
		this.enabled = value;
	}

}